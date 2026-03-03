import { inject } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';

export const roleGuard: CanActivateFn = (route, state) => {
  const authService = inject(AuthService);
  const router = inject(Router);

  const expectedRole = route.data['expectedRole'];
  

  if(authService.hasRole(expectedRole)) {
    return true;
  }

  return router.createUrlTree(['/dashboard']);
};
